# StaticAnalysisQueries

A set of Code-ql/Joern queries to find vulnerabilities

Each directory contains a set of queries useful to detect the bug/cve represented by the directory name.

All files terminating with .ql are correct code-ql queries whereas the .sc (Scala) scripts are Joern rules.

More specifically, for each directory you will find at least a ```query.ql``` file and a ```script.sc``` files. These represent 
exactly the checkers that we implemented to find the bugs in our code. In some cases we included other additional queries, that we used
for some different evaluations but that we did not include in our scan of the projects.
