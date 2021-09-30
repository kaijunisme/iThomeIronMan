function isEmail(text) {
	var re = /([\w\-]+\@[\w\-]+\.[\w\-]+)/;
	return re.test(text);
}

function isPasswordFormat(text) {
	var re = /^(?=.*[a-z])(?=.*[0-9])[a-zA-Z]{1}[a-zA-Z0-9]{5,15}$/;
	return re.test(text);
}