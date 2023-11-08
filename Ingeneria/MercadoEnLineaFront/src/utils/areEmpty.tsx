export function areEmpty(...args: string[]): boolean {
  let res: boolean = false;
  for (let a of args) {
    res = res || /^\s*$/.test(a) || !a.trim();
  }
  return res;
}
