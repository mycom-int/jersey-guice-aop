(function()
  {
    var app = angular.module('players',[]);
    
    app.controller('MusicianFinderController',['$http', function($http) {

      this.categories = [];
      this.players = [];
      this.selcat = false;

      var myself = this;

      /** get the categories */
      $http.get('./api/v1/meta/categories/').success(function(data) {
	 myself.categories=  data;
      });

      /** update the players for a given category */
      this.updatePlayers=function(selectedCategory)   {
	myself.selcat=true;
	$http.get('./api/v1/players/'+selectedCategory.value).success(function(data) {
	myself.players=data;
      });
      };	
    }]);

  }) ();
