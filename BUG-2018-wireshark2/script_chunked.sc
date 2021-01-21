

val free_args = cpg.method.name("g_free").parameter.argument.isCall.l
free_args.foreach{ x=>println(x.reachableByFlows(cpg.identifier).count.p) } 

//  cpg.identifier.isArgument || cpg.identifier.code(".*fv*.")
