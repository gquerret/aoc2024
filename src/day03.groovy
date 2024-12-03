import java.nio.file.*

def text = Paths.get('resources/day03-01.txt').text
def matcher = text =~ /mul\((\d{1,3}),(\d{1,3})\)/
def rslt = 0L
for (int zz = 0; zz < matcher.size(); zz++) {
  rslt += Integer.parseInt(matcher[zz][1]) * Integer.parseInt(matcher[zz][2])
}
println rslt

def list = []
def enabled = true
def pos1 = 0
def pos2 = text.indexOf("don't()")
while (pos2 != -1) {
  if (enabled) {
    list.add(text.substring(pos1, pos2))
    pos1 = pos2 + 7
    pos2 = text.indexOf("do()", pos1)
    enabled = false
  } else {
    pos1 = pos2 + 4
    pos2 = text.indexOf("don't()", pos1)
    if (pos2 == -1)
      list.add(text.substring(pos1))
    enabled = true
  }
}

rslt = 0L
list.each { line ->
  def matcher2 = line =~ /mul\((\d{1,3}),(\d{1,3})\)/
  for (int zz = 0; zz < matcher2.size(); zz++) {
    rslt += Integer.parseInt(matcher2[zz][1]) * Integer.parseInt(matcher2[zz][2])
  }
}
println rslt