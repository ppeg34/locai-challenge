let func i =
  let workArray = [| 1..10000 |]

  let swap loc1 loc2 =
    let tmp = Array.get workArray loc1
    Array.set workArray loc1 (Array.get workArray loc2)
    Array.set workArray loc2 tmp

  let rnd = System.Random()
  for n = (Array.length workArray) downto 2 do
    let k = rnd.Next(n)
    swap (n - 1) k

  workArray

for i = 1 to 10000 do
  func i |> ignore
