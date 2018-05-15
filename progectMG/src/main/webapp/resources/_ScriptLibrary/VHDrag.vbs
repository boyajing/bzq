'--------------------------------------------------------------------'
'  Convea 5.0 - 13022003                                             '
'  Copyright (c) 1999 - 2003 Convea Ltd.  All Rights Reserved        '
'--------------------------------------------------------------------'
' Warning: This computer program is protected by copyright law and   '
' international treaties.  Unauthorised reproduction, modification   '
' or distribution of this program, or any portion of it, may result  '
' in severe civil and criminal penalties, and will be prosecuted to  '
' the maximum extent possible under the law.                         '
'--------------------------------------------------------------------'
	Dim objMover
	Dim bolInAction
	Dim intDifferenceX
	Dim intDifferenceY
	Dim objID
	
	bolInAction = False
	
	Sub document_onMousedown
		doDragDropAction("MouseDown")
	End Sub
	
	Sub document_onMouseMove()
		doDragDropAction("MouseMove")
	End Sub
	
	Sub document_onMouseUp()
		doDragDropAction("MouseUp")
	End Sub
	
	Function document_onDragStart()
	  	If bolInAction Then document_onDragStart = False
	End Function
	
	Function document_onSelectStart()
	  	If bolInAction Then document_onSelectStart = False
	End Function
	
	Function doDragDropAction(strAction)
		On Error Resume Next
		If left(navigator.appVersion,1) < 4 Then Exit Function
		
		Select Case strAction
			
		   Case "MouseDown"
				Dim bolCancel
				Dim objElement
				If Not IsObject(window.event.srcElement) Then Exit Function
				Set objElement = window.event.srcElement
					objID=Lcase(window.event.srcElement.id)
				bolCancel = IsNull(objElement.getAttribute("DragDropEnabled")) 
				if bolCancel=false then objElement.style.zIndex=1
				
				Do Until bolCancel = False Or objElement.tagName = "BODY"
					Set objElement = objElement.ParentElement
					If Not IsObject(objElement) Then Exit Function
					bolCancel = IsNull(objElement.getAttribute("DragDropEnabled"))
				Loop
				
				If Not bolCancel Then 
					Set objMover = objElement
					bolInAction = True
					if objID="hdragbar" then
						if window.event.x>10 then 
							if window.event.x< (document.body.clientWidth-10) then 
								intDifferenceX = window.event.x - objMover.style.pixelLeft
							else 
								intDifferenceX = (document.body.clientWidth-10) - objMover.style.pixelLeft				
							end if
						end if
					end if
		
					if objID="vdragbar" then
						if window.event.y > (reservetop+10) then 
							if window.event.y < (document.body.clientHeight-10) then
								intDifferenceY = window.event.y - objMover.style.pixelTop
							else 
								intDifferenceY = (document.body.clientHeight-10) - objMover.style.pixelTop
							end if
						end if
					end if				
				End If
				
		   Case "MouseMove"
				If Not (bolInAction) Then Exit Function
					if objID="hdragbar" then
						if window.event.x>10 then 
							if window.event.x <(document.body.clientWidth-10) then
								objMover.style.Left = window.event.x - intDifferenceX
							else 
								objMover.style.Left = (document.body.clientWidth-10) - intDifferenceX
							end if
						else
							objMover.style.Left = 10
						end if
					end if
		
					if objID="vdragbar" then	
						if window.event.y > (reservetop+10) then 
							if window.event.y < (document.body.clientHeight-10) then
								objMover.style.Top = window.event.y - intDifferenceY
							else 
								objMover.style.Top = (document.body.clientHeight-10) - intDifferenceY
							end if
						else
							objMover.style.Top = reservetop+10
						end if
					end if					
		
			Case "MouseUp"
				window.event.srcElement.style.zIndex=0
				bolInAction = False: Set objMover = Nothing	

		End Select
	End Function