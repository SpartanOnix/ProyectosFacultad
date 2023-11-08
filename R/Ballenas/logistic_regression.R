library(dplyr)
library(pacman)

path <- "C:/Users/jamyn/Documents/Programacion/R/Ballenas/baleias.csv"
baleias <- read.csv(file = path)

summary(baleias)
glimpse(baleias)

# STEP 1 Modificar as vari�veis para factors

baleias$Compliance <- as.factor(baleias$Compliance)
baleias$Avoidance <- as.factor(baleias$Avoidance)
baleias$Behaviour <- as.factor(baleias$Behaviour)

# SETP 2 Criar dummies

baleias$Travel <- ifelse(baleias$Behaviour == "Travel", 1, 0)
baleias$Rest <- ifelse(baleias$Behaviour == "Rest", 1, 0)
baleias$Social <- ifelse(baleias$Behaviour == "Social", 1, 0)
#) N�O NECESS�RIO PQ AUTOMATICAMENTE J� CRIA DUMMIES? COM PACKAGE CONTRAST
#  VERIFICAMOS ISSO. Had it been a pure categorical variable with no internal
#  ordering, like, say the sex of the patient, you may leave that variable as
#  a factor itself.
glimpse(baleias)
library(contrast)
contrasts(baleias$Behaviour)


# STEP 3 Analisar as frequ�ncias das categorias da VD
table(baleias$Avoidance)
summary(baleias)

##H� elevada discrep�ncia, por isso criamos Training data #


# STEP 4 Criar traning and test samples

## Partir a data no training set e test set
library(caret)

set.seed(100)
# 70% training data
index <- createDataPartition(baleias$Avoidance, p = 0.7, list = FALSE)
train <- baleias[index, ]
test <- baleias[-index, ]


## Down Sampling
set.seed(100)
traindown <- downSample(x = train[, -ncol(train)],
                         y = train$Avoidance)

table(traindown$Avoidance)

# STEP 5 Verificar quais as categorias de refer�ncia
levels(train$Avoidance) #N�o = Categoria refer�ncia
levels(train$Compliance) #N�o = Categoria refer�ncia
levels(train$Behaviour) #Rest = Categoria refer�ncia

## Modificar a categoria de refer�ncia do compliance para ser SIM
train$Compliance <- relevel(train$Compliance, ref = "Yes")
levels(train$Compliance) #Sim = categoria refer�ncia


# STEP 6 Verificar pressupostos

## 1. Vari�vel dependente dicot�mica (categorias mutuamente exclusivas)
## 2. Independ�ncia das observa��es (sem medidas repetidas)

## Constru��o do modelo:

mod <- glm(Avoidance ~ Group_size + Compliance + Vessels + Behaviour,
             family = binomial(link = "logit"), data = train)

## 3. Verificar a aus�ncia de outliers / pontos de alavancagem
library(MASS) #para poder fazer o stdres
#5 porque o gr�fico 5 desta fun��o � espec�fica para outliers. Se tivermos um
# outlier neste gr�fico, ele estar� para al�m da linha aos pontinhos
plot(mod, which = 5)
#outra forma � olhar para os res�duos padronizados. Este teste falha quando
# est�o fora da faixa -3, +3
summary(stdres(mod))

#Deste site retirei como tratar outliers
#http://www.sthda.com/english/articles/36-classification-methods-essentials/148-logistic-regression-assumptions-and-diagnostics-in-r/ # nolint
library(broom)
plot(mod, which = 4, id.n = 3)
model.data <- augment(mod) %>% mutate(index = 1:n())
model.data %>% top_n(3, .cooksd)
ggplot(model.data, aes(index, .std.resid)) +
  geom_point(aes(color = Behaviour), alpha = .5) +
  theme_bw()
model.data %>%
  filter(abs(.std.resid) > 3)
## Passo a ter de utilizar este model.data?????

## 4. Multicolinearidade
library(psych)
pairs.panels(train)   #r> 0.9 ou 0.8

library(car)
#vif > 10 # nolint
vif(mod)

## 5. Rela��o linear entre cada VI cont�nua e o logito da VD

#Intera��o n�o pode ser significativa

intlog <- train$Vessels * log(train$Vessels)
intlog2 <- train$Group_size * log(train$Group_size)

train$intlog <- intlog
train$intlog2 <- intlog2

modint <- glm(Avoidance ~ Group_size + Compliance + Vessels + Behaviour +
              intlog + intlog2, family = binomial(link = "logit"), data = train)

summary(modint)


# Intera��o do intlog2 � significativa, pressuposto da linearidade
# n�o � cumprido.

#Tamb�m tentei testar assim a linearidade

probabilities <- predict(mod, type = "response")
logit <- log(probabilities / (1 - probabilities))

library(ggplot2)
ggplot(train, aes(logit, Vessels)) +
  geom_point(size = 0.5, alpha = 0.5) +
  geom_smooth(method = "loess") +
  theme_bw()

library(dplyr)

# STEP 7. An�lise do modelo
## Overall effects - deu erro ao correr
Anova(mod, type = "II", test = "Wald")

## Efeitos espec�ficos
summary(mod)

#Apenas Compliance e Group size s�o vari�veis significativas
#Associa��o positiva entre o n�o cumprimento da regula��o e ocorrer avoidance
#das baleias, isto �, quando n�o h� cumprimento da regula��o aumenta a odd de
#ocorrer avoidance.
#O mesmo acontece com o group size- quantas mais baleias,
#maior a odd de reagirem

## Obten��o das raz�es de odd com IC 95% (usando log-likelihood)
exp(cbind(OR = coef(mod), confint(mod)))
