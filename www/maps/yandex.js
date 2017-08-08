ymaps.ready(init);

function init () {
    var myMap = new ymaps.Map("map", {
            center: [54.83, 37.11],
            zoom: 5
        }),
        myPlacemark = new ymaps.Placemark([55.907228, 31.260503], {
            // ����� ����� � ���� ����������� �� �����, ���������� ������ �� ������������ ��������.
            balloonContentHeader: "����� �����",
            balloonContentBody: "���������� <em>������</em> �����",
            balloonContentFooter: "������",
            hintContent: "���� �����"
        });

    myMap.geoObjects.add(myPlacemark);

    // ��������� ����� �� ����� (��� �������� � ����������).
    myMap.balloon.open([51.85, 38.37], "���������� ������", {
        // �����: �� ���������� ������ ��������.
        closeButton: false
    });

    // ���������� ���� �� ����� (��� �������� � ����������).
    myMap.hint.show(myMap.getCenter(), "���������� �����", {
        // �����: �������� ����� ���������.
        showTimeout: 1500
    });
}
