/*
 * Copyright 2016 Anton Tananaev (anton.tananaev@gmail.com)
 * Copyright 2016 Andrey Kunitsyn (abyss@fox5.ru)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

Ext.define('Traccar.view.ReportConfigController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.reportConfigDialog',

    init: function () {
        var store = this.lookupReference('eventTypeField').getStore();
        if (store.getCount() === 1) {
            Ext.create('Traccar.store.AllNotifications').load({
                scope: this,
                callback: function (records, operation, success) {
                    var i, value, name, typeKey;
                    if (success) {
                        for (i = 0; i < records.length; i++) {
                            value = records[i].get('type');
                            typeKey = 'event' + value.charAt(0).toUpperCase() + value.slice(1);
                            name = Strings[typeKey];
                            store.add({type: value, name: name});
                        }
                    }
                }
            });
        }
    },

    onSaveClick: function (button) {
        var eventType;
        this.getView().callingPanel.deviceId = this.lookupReference('deviceField').getValue();
        this.getView().callingPanel.groupId = this.lookupReference('groupField').getValue();
        eventType = this.lookupReference('eventTypeField').getValue();
        if (eventType.indexOf('%') > -1) {
            eventType = ['%'];
        } else if (eventType.length === this.lookupReference('eventTypeField').getStore().getCount() - 1) {
            eventType = ['%'];
        }
        this.getView().callingPanel.eventType = eventType;
        this.getView().callingPanel.fromDate = this.lookupReference('fromDateField').getValue();
        this.getView().callingPanel.fromTime = this.lookupReference('fromTimeField').getValue();
        this.getView().callingPanel.toDate = this.lookupReference('toDateField').getValue();
        this.getView().callingPanel.toTime = this.lookupReference('toTimeField').getValue();
        this.getView().callingPanel.updateButtons();
        button.up('window').close();
    }
});
