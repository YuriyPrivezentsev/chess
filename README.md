The sample program calculating the number of possible mutual positions of set of chess pieces, except pawns, which can be placed on chess board of size MxN without taking each other.

The program contains generic command line client which takes input line in the following format:
MxN,FxQ,FxQ,...
where M and N are desk dimensions, F is figure name of Q,K,N,R,B and Q is quantity.

As the default output sample provides general number of possible solutions and time taken for calculation.

The sample demonstrates work of several algorithms:
Recursive — where attempt of placing each next piece is done recursively
Semi-Recursive — the version of previous algorithm where recursion is avoided by means of explicitly saving the part of PSW representing the valuable for calculation fields before each new iteration.

The sample also allows to choose between tree-based basic structures and array-based.

For the case of saving results to csv file generic client takes as the first command line argument alternative path to csv file.

Maven module has three test profiles:
unit-tests (default) — includes only light-weight unit tests
integration-tests — includes end-to-end test on known result of the composite system
all-tests — aggregation of two above.

Since application is planned as standalone and not as pluggable maven assembly builds runnable jar with logging framework dependencies included.