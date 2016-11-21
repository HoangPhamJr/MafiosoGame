function cleanUpGamesAndPlayers(){
  var cutOff = moment().subtract(2, 'hours').toDate().getTime();

  var numGamesRemoved = Games.remove({
    createdAt: {$lt: cutOff}
  });

  var numPlayersRemoved = Players.remove({
    createdAt: {$lt: cutOff}
  });
}

function getRandomLocation(count){
  //var locationIndex = Math.floor(Math.random() * locations.length);
  return locations[count];
}

function shuffleArray(array) {
    for (var i = array.length - 1; i > 0; i--) {
        var j = Math.floor(Math.random() * (i + 1));
        var temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    return array;
}

function getRoleList(array) {
    var mapping = [array.length];
    for (var i = array.length - 1; i >= 0; i--) {
        mapping[i] = {name:array[i]};
    }
    return mapping;
}

function assignRoles(players, location){
  var default_role = location.roles[location.roles.length - 1];
  var roles = location.roles.slice();
  var shuffled_roles = shuffleArray(roles);
  var role = null;

  players.forEach(function(player){
    if (!player.isSpy){
      role = shuffled_roles.pop();
      if (role === undefined){
        role = default_role;
      }

      var description = role.toString().replace("name", "description");
      Players.update(player._id, {$set: {role: role}});
      Players.update(player._id, {$set: {description: description}});
      if(role.toString().indexOf("mafi")!== -1){
        Players.update(player._id, {$set: {isSpy: true}});
      }
    }
  });
}

Meteor.startup(function () {
  // Delete all games and players at startup
  Games.remove({});
  Players.remove({});
});

var MyCron = new Cron(60000);

MyCron.addJob(5, cleanUpGamesAndPlayers);

Meteor.publish('games', function(accessCode) {
  return Games.find({"accessCode": accessCode});
});

Meteor.publish('players', function(gameID) {
  return Players.find({"gameID": gameID});
});

Games.find({"state": 'settingUp'}).observeChanges({
  added: function (id, game) {
    var players = Players.find({gameID: id});
    var location = getRandomLocation(parseInt(players.count()-1));
    var gameEndTime = moment().add(game.lengthInMinutes, 'minutes').valueOf();

    var spyIndex = Math.floor(Math.random() * players.count());
    var firstPlayerIndex = Math.floor(Math.random() * players.count());

    /*players.forEach(function(player, index){
      Players.update(player._id, {$set: {
        isSpy: index === spyIndex,
        isFirstPlayer: index === firstPlayerIndex
      }});
    });*/

    assignRoles(players, location);
    var roleList = getRoleList(location.roles.slice());
    Games.update(id, {$set: {state: 'inProgress', location: location, endTime: gameEndTime, paused: false, pausedTime: null, roleList: roleList, id: id, currentMode: 0}});
  }
});