describe("A simple suite", function() {
	
	var blogEntry;

	beforeEach(function(){
		blogEntry = new BlogEntry.Instance();
	});

	it("tests the split(val) function", function() {
		expect(blogEntry.split('hola, amigo')).toEqual(['hola','amigo']);
	});

	it("tests the extractlast(term) function",function(){
		expect(blogEntry.extractLast('hola, amigo')).toBe('amigo');
	});

	it("tests the createFrequencyLabelString(key,val) ",function(){
		expect(blogEntry.createFrequencyLabelString("java",2)).toBe("java(2) ");	
	});

});
