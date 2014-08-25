(function()
  {
    var app = angular.module('players',[]);
    
    app.controller('MusicianFinderController',['$http', function($http) {

      this.categories = [];
      this.players = [];
      var myself = this;

      /** get the categories */
      $http.get('./api/v1/meta/categories/').success(function(data) {
	 myself.categories=  data;
      });

      /** update the players for a given category */
      this.updatePlayers=function(selectedCategory)   {
	$http.get('./api/v1/players/'+selectedCategory.value).success(function(data) {
	myself.players=data;
      });
      };	
    }]);

  }) ();
