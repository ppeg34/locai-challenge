let generateArray =
  let workArray = [| 1..10000 |]

  // swap two indexes in workArray
  let swap loc1 loc2 =
    let tmp = Array.get workArray loc1
    Array.set workArray loc1 (Array.get workArray loc2)
    Array.set workArray loc2 tmp

  let rnd = System.Random()
  // iterate on successively smaller chunks of workArray
  // swap a random element in chunk with last item in chunk
  for n = (Array.length workArray) downto 2 do
    let k = rnd.Next(n)
    swap (n - 1) k

  workArray

generateArray |> Seq.map string |> String.concat "," |> printfn "%s"
