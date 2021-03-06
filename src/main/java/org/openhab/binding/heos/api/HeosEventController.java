package org.openhab.binding.heos.api;

import static org.openhab.binding.heos.resources.HeosConstants.*;

import org.openhab.binding.heos.handler.HeosBridgeHandler;
import org.openhab.binding.heos.resources.HeosCommands;
import org.openhab.binding.heos.resources.HeosResponse;
import org.openhab.binding.heos.resources.MyEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HeosEventController extends MyEventListener {

    private HeosResponse response = null;
    private HeosSystem system = null;
    private HeosCommands command = null;
    private String eventType = null;
    private String eventCommand = null;

    private Logger logger = LoggerFactory.getLogger(HeosBridgeHandler.class);

    public HeosEventController(HeosResponse response, HeosCommands command, HeosSystem system) {
        this.response = response;
        this.system = system;
        this.command = command;
    }

    public void handleEvent(int client) {

        if (client == 0) {
            logger.debug("HEOS send response: {}", response.getRawResponseMessage());
        } else if (client == 1) {
            logger.debug("HEOS event response: {}", response.getRawResponseMessage());
        }

        if (response.getEvent().getResult().equals(FAIL)) {

            String errorCode = response.getEvent().getErrorCode();
            String errorMessage = response.getEvent().getErrorMessage();

            logger.warn("HEOS System response failure with error code '{}' and message '{}'", errorCode, errorMessage);

            return;
        } else {
            this.eventType = response.getEvent().getEventType();
            this.eventCommand = response.getEvent().getCommandType();

            switch (eventType) {

                case "event":
                    eventTypeEvent();
                    break;
                case "player":
                    eventTypePlayer();
                    break;
                case "system":
                    eventTypeSystem();
                    break;
                case "browse":
                    eventTypeBrowse();
                    break;
                case "group":
                    eventTypeGroup();
                    break;

            }

        }
    }

    private void eventTypeEvent() {

        switch (eventCommand) {

            case "player_now_playing_progress":
                break;
            case "players_changed":
                fireBridgeEvent("event", null, eventCommand);
                break;
            case "player_now_playing_changed":
                mediaStateChanged();
                break;
            case "player_state_changed":
                playerStateChanged();
                break;
            case "player_queue_changed":
                break;
            case "sources_changed":
                break;
            case "player_volume_changed":
                volumeChanged();
                break;
            case "groups_changed":
                fireBridgeEvent("event", null, eventCommand);
                break;
            case "user_changed":
                userChanged();
                break;

        }
    }

    private void eventTypePlayer() {

        switch (eventCommand) {

            case "get_now_playing_media":
                break;
            case "get_player_info":
                break;
            case "get_play_state":
                playerStateChanged();
                break;
            case "get_volume":
                break;
            case "get_mute":
                break;
            case "get_queue":
                break;
            case "set_play_state":
                break;
            case "set_volume":
                break;

        }
    }

    private void eventTypeBrowse() {

        switch (eventCommand) {

            case "get_music_sources":
                break;
            case "browse":
                break;

        }
    }

    private void eventTypeSystem() {
        switch (eventCommand) {

            case COM_SING_IN:
                signIn();
                break;
        }

    }

    private void eventTypeGroup() {
        // not implemented yet
    }

    private void playerStateChanged() {

        String pid = response.getPid();
        String event = "state";
        String command = response.getEvent().getMessagesMap().get("state");
        fireStateEvent(pid, event, command);
    }

    private void volumeChanged() {
        String pid = response.getPid();
        String event = "volume";
        String command = response.getEvent().getMessagesMap().get("level");
        fireStateEvent(pid, event, command);
        event = "mute";
        command = response.getEvent().getMessagesMap().get("mute");
        fireStateEvent(pid, event, command);

    }

    private void mediaStateChanged() {
        String pid = response.getPid();
        system.send(command.getNowPlayingMedia(pid));
        fireMediaEvent(pid, response.getPayload().getPayloadList().get(0));

    }

    private void signIn() {

        if (response.getEvent().getMessagesMap().get(COM_UNDER_PROCESS).equals(FALSE)) {
            fireBridgeEvent(EVENTTYPE_SYSTEM, SUCCESS, COM_SING_IN);
        }
    }

    private void userChanged() {
        fireBridgeEvent(EVENTTYPE_SYSTEM, SUCCESS, COM_USER_CHANGED);
    }

    public void connectionToSystemLost() {
        fireBridgeEvent(EVENTTYPE_EVENT, FAIL, CONNECTION_LOST);
    }

    public void connectionToSystemRestored() {
        fireBridgeEvent(EVENTTYPE_EVENT, SUCCESS, CONNECTION_RESTORED);
    }

}
