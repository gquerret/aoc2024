import java.nio.file.*

def reports = []

def textFile = Paths.get('resources/day02-01.txt')
textFile.readLines().collect { line ->
  line.split(' ').collect(it -> Integer.parseInt(it))
}
.forEach {
  reports.add(it)
}

def count = 0
def count2 = 0
reports.each { it ->
  if (isSafe(it))
    count++
  if (isSafeV2(it))
    count2++
}

println count
println count2

def isSafe(report) {
  def diff = report.withIndex().collect{it, idx -> 
    idx == 0 ? 0 : report[idx] - report[idx - 1]
  }

  return (diff.tail().findAll(it -> (it > 0) && (it <= 3)).size() == diff.size() - 1) ||
        (diff.tail().findAll(it -> (it < 0) && (it >= -3)).size() == diff.size() - 1)
}

def isSafeV2(report) {
  if (isSafe(report))
    return true
  for (int zz = 0; zz < report.size(); zz++) {
    if (isSafe(subList(report, zz)))
      return true
  }
  return false
}

def subList(report, idx) {
  def begin = report.take(idx)
  begin.addAll(report.takeRight(report.size() - idx - 1))
  return begin
}
