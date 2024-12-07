import java.nio.file.*

def textFile = Paths.get('resources/day07-01.txt')
def lines = textFile.readLines().collect { line ->
  def rslt = Long.parseLong(line.substring(0, line.indexOf(':')))
  def numbers = line.substring(line.indexOf(':') + 1).trim().tokenize(' ').collect(it -> Long.parseLong(it))
  return [ rslt: rslt, numbers: numbers ]
}

def sum1 = 0L
def sum2 = 0L
def time = System.currentTimeMillis()
lines.each { line ->
  if (isValidV1(line.rslt, line.numbers[0], line.numbers.tail()))
    sum1 += line.rslt
  if (isValidV2(line.rslt, line.numbers[0], line.numbers.tail()))
    sum2 += line.rslt
}
println "Result 1: ${sum1} -- Result 2: ${sum2} in ${System.currentTimeMillis() - time} ms"


def isValidV1(result, curr, list) {
  if (curr > result)
    return false
  if (list.size() == 1)
   return (curr + list.get(0) == result) || (curr * list.get(0) == result)
  else
    return isValidV1(result, curr + list.get(0), list.tail()) || isValidV1(result, curr * list.get(0), list.tail())
}

def isValidV2(result, curr, list) {
  if (curr > result)
    return false
  if (list.size() == 1)
   return (curr + list.get(0) == result) || (curr * list.get(0) == result) || (Long.parseLong(Long.toString(curr) + Long.toString(list.get(0))) == result)
  else
    return isValidV2(result, curr + list.get(0), list.tail()) || isValidV2(result, curr * list.get(0), list.tail()) || isValidV2(result, Long.parseLong(Long.toString(curr) + Long.toString(list.get(0))), list.tail())
}
