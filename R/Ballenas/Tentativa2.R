library(dplyr)
library(pacman)
library(groupdata2)
last = read.csv('C:/Users/jamyn/Documents/Programacion/R/Ballenas/baleias.csv')
summary(last)
glimpse(last)
table(train$Avoidance)
# STEP 1 Modificar as vari?veis para factors

last$Compliance <- as.factor(last$Compliance)
last$Avoidance <- as.factor(last$Avoidance)



# STEP 4 Criar traning and test samples 

## Partir a data no training set e test set
library(caret)

set.seed(101)
index <- createDataPartition(last$Avoidance, p=0.7, list = F)  # 70% training data
train <- last[index, ]
test  <- last[-index, ]

table(train$Avoidance)
table(test$Avoidance)


#set.seed(101)
#Remove a coluna Avoidance que est? na posi??o 2
#A nova coluna chamada "class" ganha depois o nome "Avoidance"
upsampling <- upsample( train, cat_col = "Group_size", id_method = "n_ids")
upsampling <- upsample( upsampling, cat_col = "Avoidance", id_method = "n_ids") #volta a dar o nome
View(upsampling)

#Confirmar que o nr de Avoidances cresceu, balanceando.
table(upsampling$Avoidance)
View(train)
View(upsampling)

# STEP 5 Verificar quais as categorias de refer?ncia
levels(upsampling$Avoidance) #N?o = Categoria refer?ncia


## Modificar a categoria de refer?ncia do compliance para ser SIM
upsampling$Compliance <- relevel(upsampling$Compliance, ref="Yes")
levels(upsampling$Compliance) #Sim = categoria refer?ncia


## Constru??o do modelo:


mod_last1 <- glm(Avoidance ~ Group_size + Compliance + Vessels, family= binomial(link='logit'), data= upsampling)

summary(mod_last1)



#Overall efffect an?lise/ Teste raz?o de verosimilhan?a (adiciona as vari?veis sequencialmente)

anova(mod_last1, test="Chisq")

#Efeitos espec?ficos
summary(mod_last1)

## Obten??o das raz?es de odd com IC 95% (usando log-likelihood)
exp(cbind(OR = coef(mod_last1), confint.default(mod_last1)))

library(sjPlot)
plot_model(mod_last1, vline.color= "red", sort.est = TRUE, show.values= TRUE, value.offset = .3)

library(car)
##Diagn?stico do Modelo##
marginalModelPlots(mod_last1)

View(last)

#Outliers
car::outlierTest(mod_last1) #sem outliers

#Multicolinearidade
car::vif(mod_last1) #sem valores superiores a 5


## Gr?fico dos efeitos ###
library(effects)
plot(allEffects(mod_last1))


#Previs?o do modelo#

library(InformationValue)
# type = "response" gives the predicted probabilities. 
predicted <- predict(mod_last1, test, type="response")
#mostra probabilidades de Avoidance
hist(predicted) 
# ESTE comando s? pode ser feito UMA VEZ
# porque o "Yes" vai desaparecer!!!
test$Avoidance <- ifelse(test$Avoidance=="Yes", 1, 0)
table(test$Avoidance) #tem que existir 0s e 1s !

### Desempenho do modelo ###
## Matriz de confus?o ## 
# Pode variar-se optimiseFor para fazer experi?ncias
# Todas as experi?ncias devem ser documentadas no texto 
# da disserta??o
optimal <- optimalCutoff(test$Avoidance, predicted, optimiseFor = "Both")
optimal
# Apenas para testar outro "threshold" que 
# calibre, a olho, o melhor o modelo
optimal = 0.5
optimal
# Todos precisam conhecer o novo threshold
confusionMatrix(test$Avoidance, predicted, threshold=optimal)

sensitivity(test$Avoidance, predicted, threshold=optimal)

specificity(test$Avoidance, predicted, threshold=optimal)

misClassError(test$Avoidance, predicted, threshold=optimal)


#Acuracy = (1-misClassError)

#ALTERNATIVA DA PREVIS?O # 

pred <- predict(mod_last1, test, type= "response")
result <- as.factor(ifelse(pred > 0.5,1,0))

library(caret)
confusionMatrix(result, test$Avoidance, positive= "Yes")


plotROC(test$Avoidance, predicted)


## Exportar a tabela do modelo ##
library(sjPlot)
tab_model(mod_last1)


 # H? mais t?cnicas al?m da regress?o log?stica!!! Esta pode
# n?o ser a melhor delas mas importa, acho que fique 
# bem estudada. 


# Ao fazer o upsampling o "Vessels" passa a significativo.
# Pode fazer sentido do ponto de vista da experiencia (existir 
# uma explica??o "biologica" para o fen?meno)

#Plotar cada vari?vel significativa "contra" o avoidance para compreender visualmente a sua rela??o
#Traz valores de probabilidade de acordo com os valores 
library(effects)
plot(allEffects(mod_last1))


OU 

library(sjPlot)
library(ggplot2)

plot_model(mod_last1, type = "pred", terms = "Group_size")
plot_model(mod_last1, type = "pred", terms = "Vessels")
plot_model(mod_last1, type = "pred", terms = "Compliance")

 ### MODELO 2 SEM VESSELS
mod2 <- glm(Avoidance ~ Group_size + Compliance, family= binomial(link='logit'), data= upsampling)
summary(mod2)

anova(mod_last1, test="Chisq")

anova(mod2, mod_last1, test="Chisq")
AIC(mod2, mod_last1)


