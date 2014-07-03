$(function() {
  var $iframe = $('#iframe-content');
  var $window = $(window);
  var $frameLoading = $('#loading-msg');
  
  var onResize = function(){
    $iframe.height($window.height() - 100);
  };

  $iframe.load(function () {
    $frameLoading.hide();
    $iframe.show();
    onResize();
  });
  $window.resize(onResize);

});
