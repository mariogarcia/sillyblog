<r:script>
	new BlogEntry.Instance().
		createFrequencyList(
			"#tagCloud",
			'${createLink(controller:'blogEntryTags',action: 'getTagFrequency')}'
		);
</r:script>
