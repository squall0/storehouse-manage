var slideout = new Slideout({
	'panel' : document.getElementById('panel'),
	'menu' : document.getElementById('menu'),
	'padding' : 128,
	'tolerance' : 70
});

// Toggle button
document.querySelector('#tab1').addEventListener('click', function() {
	slideout.toggle();
});
$(".oncl").click(function() {
	slideout.close();
});