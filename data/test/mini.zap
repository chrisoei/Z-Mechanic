GLOBAL::                        ; global vars would go here
OBJECT::                        ; objects would go here
IMPURE::                        ; end of dynamic memory
VOCAB::
        .BYTE 0                 ; # of breaking punctuation marks
        .BYTE 4                 ; entry length (not that we have any)
        .WORD 0                 ; # of entries
WORDS::
ENDLOD::
START::
        print_char 67              ; 'c'
	QUIT
	.END
