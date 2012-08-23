
/**
 * Creates a datepicker widget in the given element
 * with the given format
 * */
function createDatepicker(elementId,format){
	$(elementId).datepicker({
		dateFormat:format,
		onClose:function(dateText,inst){
		 /* Workaround for spanish year format and jquery's datepicker format */
			var selectedDate = Date.parseExact(dateText,format.replace("yy","yyyy"));
		 /* And then set all date's fields */
			$(elementId + UNDERSCORE + 'day').val(selectedDate.getDate())
			$(elementId + UNDERSCORE + 'month').val(selectedDate.getMonth() + 1)
			$(elementId + UNDERSCORE + 'year').val(selectedDate.getFullYear())
		}
	});
}

/**
 * Creates a multiple autocomplete. It source comes from the action
 * passed as the second parameter.
 * */
function createAutocomplete(elementId, action){
	var blogEntry = new BlogEntry.Instance();
	$(elementId).
			bind("keydown", function(event){
				if(event.keyCode == $.ui.keyCode.TAB && 
					$(this).data("autocomplete").menu.active){
					event.preventDefault();
				}		
			}).
			autocomplete({
				minLenght:0,
				source: function(request,response){
					$.getJSON(
						action,
						{name:blogEntry.extractLast(request.term)},
						function(result){
							response($.map(result,function(item){
								return item.name;
							}));
						}	
					);
				},
				focus: function(){return false;},
				select: function(event,ui){
					var terms = blogEntry.split(this.value);
					var joinPattern = ", ";
					terms.pop();
					terms.push(ui.item.value);
					terms.push(EMPTY);
					this.value = terms.join(joinPattern);
					return false;
				}	
			});
}
