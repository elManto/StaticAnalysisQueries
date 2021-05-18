
@main def execute(payload: String) = {

	importCpg(payload)

	def free_args = cpg.method.name(".*free*.").parameter.argument

	def all_free_flows = free_args.reachableBy(free_args).toList
	
	def definition_free_flows = free_args.reachableBy(free_args).dedup.toList
	
	all_free_flows.diff(definition_free_flows)
	//all_free_flows.diff(definition_free_flows).size
	
	// If we want to see the path between the two free():
	//     def freed_twice = all_free_flows.diff(definition_free_flows)
	//     cpg.method.name("g_free").parameter.argument.reachableByFlows(two_freed_vars).p
}
