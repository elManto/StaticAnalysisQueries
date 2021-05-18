@main def execute(payload: String) = {
	importCpg(payload)

        cpg.method
          .name(".*free*.")
          .filter(_.parameter.size == 1)
          .callIn
          .where(_.argument(1).isIdentifier)
          .flatMap(f => {
            val freedIdentifierCode = f.argument(1).code
            val postDom = f.postDominatedBy.toSet

            val assignedPostDom = postDom.isIdentifier
              .where(_.inAssignment)
              .codeExact(freedIdentifierCode)
              .flatMap(id => id ++ id.postDominatedBy)

            postDom
              .removedAll(assignedPostDom)
              .isIdentifier
              .codeExact(freedIdentifierCode)
          }).p

}
