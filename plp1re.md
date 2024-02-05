# PLp1: A Dynamically Typed Functional Language for Instruction - Lexical Properties

PLp1 (PL point 1) is a programming language designed for practice interpretation.
PLp1 is a simple dynamically typed language that contains elements of functional, object-oriented and imperative paradigms.  PLp1 is intended to be simple enough to interpret in a
single semester by any student willing to put in some effort. Each feature
included in the language was added specifically to help students learn about the meaning of programs written in different paradigms. The goal of this project is to help student understand what it means to express something in a language by writing an operational semantics (fancy words for interpreter). In the process, I hope that you will understand object-oriented design better and also learn about functional programming.

## Lexical Properties of PLp1

1.  In PLp1, blanks are significant.
1.  In PLp1, all keywords are reserved; that is, the programmer cannot use an PLp1 keyword as the name of a variable.

	The valid keywords are:

	$\begin{array}{lcl}
	\langle \mathbf{CASE} \rangle & \rightarrow & \texttt{case}\\
	\langle \mathbf{CLASS} \rangle & \rightarrow & \texttt{class}\\
	\langle \mathbf{CREATE} \rangle & \rightarrow & \texttt{create}\\
	\langle \mathbf{DEFAULT} \rangle & \rightarrow & \texttt{default}\\
	\langle \mathbf{ENDIF} \rangle & \rightarrow & \texttt{endif}\\
	\langle \mathbf{ELSE} \rangle & \rightarrow & \texttt{else}\\
	\langle \mathbf{FALSE} \rangle & \rightarrow & \texttt{false}\\
	\langle \mathbf{FUNCTION} \rangle & \rightarrow & \texttt{function}\\
	\langle \mathbf{IF} \rangle & \rightarrow & \texttt{if}\\
	\langle \mathbf{INIT} \rangle & \rightarrow & \texttt{init}\\
	\langle \mathbf{LAMBDA} \rangle & \rightarrow & \texttt{lambda}\\
	\langle \mathbf{LET} \rangle & \rightarrow & \texttt{let}\\
	\langle \mathbf{METHOD} \rangle & \rightarrow & \texttt{method}\\
	\langle \mathbf{NULL} \rangle & \rightarrow & \texttt{null}\\
	\langle \mathbf{SWITCH} \rangle & \rightarrow & \texttt{switch}\\
	\langle \mathbf{THEN} \rangle & \rightarrow & \texttt{then}\\
	\langle \mathbf{TRUE} \rangle & \rightarrow & \texttt{true}\\
	\end{array}$


	Note that PLp1 is *case sensitive*, that is, the variable
`X` differs from `x`.  Thus, `switch` is a keyword, but
`SWITCH` be a variable name.


1. The following special characters have meanings in an PLp1
program. 

	$\begin{array}{lcl}
	\langle \mathbf{AND} \rangle & \rightarrow & \texttt{\&}\\
	\langle \mathbf{ASSIGN} \rangle & \rightarrow & \texttt{=}\\
	\langle \mathbf{COMMA} \rangle & \rightarrow & \texttt{,}\\
	\langle \mathbf{COLON} \rangle & \rightarrow & \texttt{:}\\
	\langle \mathbf{DIVIDE} \rangle & \rightarrow & \texttt{/}\\
	\langle \mathbf{DOT} \rangle & \rightarrow & \texttt{.}\\
	\langle \mathbf{EQUAL} \rangle & \rightarrow & \texttt{==}\\
	\langle \mathbf{GT} \rangle & \rightarrow & \texttt{>}\\
	\langle \mathbf{GE} \rangle & \rightarrow & \texttt{>=}\\
	\langle \mathbf{INVOKE} \rangle & \rightarrow & \texttt{->}\\
	\langle \mathbf{LB} \rangle & \rightarrow & \texttt{\{}\\
	\langle \mathbf{LBK} \rangle & \rightarrow & \texttt{[}\\
	\langle \mathbf{LT} \rangle & \rightarrow & \texttt{<}\\
	\langle \mathbf{LE} \rangle & \rightarrow & \texttt{<=}\\
	\langle \mathbf{LP} \rangle & \rightarrow & \texttt{(}\\
	\langle \mathbf{MINUS} \rangle & \rightarrow & \texttt{-}\\
	\langle \mathbf{MULTIPLY} \rangle & \rightarrow & \texttt{*}\\
	\langle \mathbf{NOT} \rangle & \rightarrow & \texttt{!}\\
	\langle \mathbf{NE} \rangle & \rightarrow & \texttt{!=}\\
	\langle \mathbf{OR} \rangle & \rightarrow & \texttt{|}\\
	\langle \mathbf{PLUS} \rangle & \rightarrow & \texttt{+}\\
	\langle \mathbf{RB} \rangle & \rightarrow & \texttt{\}}\\
	\langle \mathbf{RBK} \rangle & \rightarrow & \texttt{]}\\
	\langle \mathbf{RP} \rangle & \rightarrow & \texttt{)}\\
	\end{array}$



1. Comments are delimited by a `//`. All characters following the `//` on the same line are part of the comment.


1.  Identifiers are written with upper and
lowercase letters and are defined
as follows:

	$\begin{array}{l c l}
	\langle \mathbf{ALPHA} \rangle&\rightarrow&\texttt{a} \; \mid \; \texttt{b} \; \mid \; \texttt{c} \; \mid \; \cdots \; \mid \; \texttt{z} \; \mid \; \texttt{A} \; \mid \; \texttt{B} \; \mid \; \cdots \; \mid \; \texttt{Z}\\
	\langle \mathbf{DIGIT} \rangle &\rightarrow&\texttt{0} \; \mid \; \texttt{1} \; \mid \; \texttt{2} \; \mid \; \cdots \; \mid \; \texttt{9}\\
	\langle \mathbf{IDENTIFIER} \rangle &\rightarrow&\langle \mathbf{LETTER} \rangle \; (\langle \mathbf{LETTER} \rangle \; \mid \langle \mathbf{DIGIT} \rangle \; )^{*}\\
	\end{array}$

	Note that all keywords can be generated with the regular expressions for identifiers. However, keywords are a special case and cannot be used as identifiers.

1.  Constants are defined as follows:

	$\begin{array}{l c l}
	\langle \mathbf{POSITIVE} \rangle &\rightarrow&\texttt{1} \; \mid \; \texttt{2} \; \mid \; \texttt{3} \; \mid \; \ldots \; \mid \; \texttt{9}\\
	\langle \mathbf{INTNUM} \rangle &\rightarrow&\langle \mathbf{POSITIVE} \rangle \; \langle \mathbf{DIGIT} \rangle^{*} \; \mid \; {\tt 0}\\
	\langle\mathbf{FLOATNUM}\rangle & \rightarrow & \langle \mathbf{INTNUM} \rangle \; (\;\langle \mathbf{DOT} \rangle \;(\;\langle \mathbf{DIGIT} \rangle\; )^*\\
	\langle \mathbf{STRING} \rangle & \rightarrow & \textrm`\;\sim(\;\;\textrm' \;\;)^* \;\textrm'\\
	\end{array}$

1. Whitespace, newline, tab, and spaces may only appear withing strings and comments. In all other contexts, encountering a space indicates the end of a lexeme.