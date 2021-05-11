
//val identifiers = cpg.call.name("g_free").argument.ast.isIdentifier.p
//val use = cpg.call.name("<operator>.indirectFieldAccess").argument
//val free = cpg.call.name("g_free").argument
//
//cpg.call.name("<operator>.indirectFieldAccess").argument.foreach{x=>println(x.lineNumber, " ", x.code, " ", x.reachableBy(cpg.parameter).lineNumber.p)}


val free = cpg.call.name("g_free").argument.reachableBy(cpg.parameter).p

val use = cpg.call.name("<operator>.indirectFieldAccess").argument.reachableBy(cpg.parameter).p
use.intersect(free).foreach(x=>println(x.code))
