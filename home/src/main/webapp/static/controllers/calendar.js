/*
*  AngularJs Fullcalendar Wrapper for the JQuery FullCalendar
*  API @ http://arshaw.com/fullcalendar/
*
*  Angular Calendar Directive that takes in the [eventSources] nested array object as the ng-model and watches it deeply changes.
*       Can also take in multiple event urls as a source object(s) and feed the events per view.
*       The calendar will watch any eventSource array and update itself when a change is made.
*
*/

//angular.module('myCalendarApp', ['ngRoute'])


app.controller('calendarCtrl', ['$scope', '$http', 'uiCalendarConfig', function ($scope, $http, uiCalendarConfig) {
    
    $scope.SelectedEvent = null;
    var isFirstTime = true;
 
    $scope.events = [];
    $scope.eventSources = [$scope.events];
 
 
    //Load events from server
    $http.get('events/find', {
        cache: true,
        params: {}
    }).then(function (data) {
    	console.log(data);
        $scope.events.slice(0, $scope.events.length);
        angular.forEach(data.data, function (value) {
        	var length = value.photos.length
        	if(length > 0){
        		$scope.hasPhoto = true;
        		var image = value.photos[0].image;
        	}
        	else{
        		$scope.hasPhoto = false;
        		var image = undefined;
        	}
        	$scope.events.push({
        		title: value.eventTitle,
                description: value.eventDescription,
                start: new Date(parseInt(value.startTime)),
                end: new Date(parseInt(value.endTime)),
                startTime: value.startTime,
                endTime: value.endTime,
                capacity: value.maxCapacity,
                id: value.eventId,
                image: image,
//                allDay : value.IsFullDay,
                stick: true
            });
        });
    });
 
    //configure calendar
    $scope.uiConfig = {
        calendar: {
            height: 450,
            editable: true,
            displayEventTime: false,
            header: {
                left: 'month basicWeek basicDay agendaWeek agendaDay',
                center: 'title',
                right:'today prev,next'
            },
            eventClick: function (event) {
                $scope.SelectedEvent = event;
            },
            eventAfterAllRender: function () {
                if ($scope.events.length > 0 && isFirstTime) {
                    //Focus first event
                    uiCalendarConfig.calendars.myCalendar.fullCalendar('gotoDate', $scope.events[0].start);
                    isFirstTime = false;
                }
            }
        }
    };
 
}])