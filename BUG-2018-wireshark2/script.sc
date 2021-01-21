// Such a script detect a possible double free flow within a function

cpg.method.name("g_free").parameter.argument.reachableByFlows(cpg.identifier).p

// Or more generically

cpg.call(".*free*.").argument.reachableByFlows(cpg.identifier).p
