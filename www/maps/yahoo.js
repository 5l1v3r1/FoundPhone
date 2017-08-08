function initialize_ymap() {
 
    var yPoint = new YGeoPoint(51.0532, 31.83);//(долгота, широта)
 
    var map = new YMap(document.getElementById('ymap'));//инициализация карты
 
    map.setMapType(YAHOO_MAP_SAT);//тип карты
 
    map.drawZoomAndCenter(yPoint, 6);//масштаб
 
    map.addTypeControl();//добавление контроля типа
 
    YEvent.Capture(map, EventsList.MouseClick, reportPosition);  //событие нажатия на карте
        //функция добавления маркера
        function reportPosition(_e, _c){  
            var mapmapCoordCenter = map.convertLatLonXY(map.getCenterLatLon()); //переобразование координат  
            var currentGeoPoint = new YGeoPoint( _c.Lat, _c.Lon );  //Точка куда нужно поставить маркер
            map.addMarker(currentGeoPoint); //Добавление маркера
        }   
}