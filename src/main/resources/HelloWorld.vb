Module VBModule
    Sub Main()
        Dim commandText As String = "SELECT * FROM Titles JOIN Publishers " _
            & "ON Publishers.PubId = Titles.PubID " _
            & "WHERE Publishers.State = 'CA'"
        Console.WriteLine(commandText)
        Dim a As String = "AB":a = a & "CD"
        Console.WriteLine(a)
        Dim intVariable% = 12
        Console.WriteLine(intVariable)
        
        Dim floatVariable# = .12
        Console.WriteLine(floatVariable)
        
        Dim longVariable& = 99999999999
        Console.WriteLine(longVariable)
        
        Dim stringVariable$ = "String variable"
        Console.WriteLine(stringVariable)
        
        Dim booleanVariable = True
        Console.WriteLine(booleanVariable)
        
        booleanVariable = False
        Console.WriteLine(booleanVariable)
        
        intVariable = 1%
        Console.WriteLine(intVariable)
        
        floatVariable = 10#
        Console.WriteLine(floatVariable)
        
        Dim ch As Char = "A"c
        Console.WriteLine(ch)
        
        Dim myDate As Date = #5/31/1993#
        Console.WriteLine(myDate)
        
        Dim nothingInt As Integer = Nothing
        Console.WriteLine(nothingInt)
        
        'Some comment
        
            Console.WriteLine((2 + 3) * 5)
    End Sub
End Module
