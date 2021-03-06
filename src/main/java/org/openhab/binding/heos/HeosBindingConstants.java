/**
 * Copyright (c) 2014-2016 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.heos;

import java.util.Set;

import org.eclipse.smarthome.core.thing.ThingTypeUID;
import org.eclipse.smarthome.core.thing.type.ChannelTypeUID;

import com.google.common.collect.Sets;

/**
 * The {@link HeosBinding} class defines common constants, which are
 * used across the whole binding.
 *
 * @author Johannes Einig - Initial contribution
 */
public class HeosBindingConstants {

    public static final String BINDING_ID = "heos";

    // List of all Bridge Type UIDs

    public final static ThingTypeUID THING_TYPE_BRIDGE = new ThingTypeUID(BINDING_ID, "bridge");
    public final static ThingTypeUID THING_TYPE_PLAYER = new ThingTypeUID(BINDING_ID, "player");
    public final static ThingTypeUID THING_TYPE_GROUP = new ThingTypeUID(BINDING_ID, "group");

    // List off all Channel Types

    public final static ChannelTypeUID CH_TYPE_PLAYER = new ChannelTypeUID(BINDING_ID, "ch_player");
    public final static ChannelTypeUID CH_TYPE_FAVORIT = new ChannelTypeUID(BINDING_ID, "ch_favorit");
    public final static ChannelTypeUID CH_TYPE_GROUP = new ChannelTypeUID(BINDING_ID, "ch_group");

    // List of all Channel ids
    public final static String CH_ID_CONTROL = "Control";
    public final static String CH_ID_VOLUME = "Volume";
    public final static String CH_ID_MUTE = "Mute";
    public final static String CH_ID_UNGROUP = "Ungroup";
    public final static String CH_ID_SONG = "Titel";
    public final static String CH_ID_ARTIST = "Interpret";
    public final static String CH_ID_ALBUM = "Album";
    public final static String CH_ID_PLAYER = "Player";
    public final static String CH_ID_BUILDGROUP = "BuildGroup";
    public final static String CH_ID_DYNGROUPSHAND = "DynamicGroupHandling";
    public final static String CH_ID_REBOOT = "Reboot";
    public final static String CH_ID_IMAGE_URL = "Image_URL";
    public final static String CH_ID_PLAYLISTS = "Playlists";
    public final static String CH_ID_INPUTS = "Inputs";
    public final static String CH_ID_STATUS = "OnlineStatus";

    public final static String HOST = "ipAddress";
    public final static String PLAYER_TYPE = "model";
    public final static String NAME = "name";
    public final static String USER_NAME = "userName";
    public final static String PASSWORD = "password";
    public final static String HEART_BEAT = "heartBeat";

    public final static String LEADER = "leader";

    public final static String PLAYER = "Player";
    public final static String GROUP = "Group";
    public final static String TYPE = "type";

    public final static String ONLINE = "ONLINE";
    public final static String OFFLINE = "OFFLINE";

    public final static String STATE = "state";
    public final static String PLAY = "play";
    public final static String PAUSE = "pause";
    public final static String STOP = "stop";
    public final static String ON = "on";
    public final static String OFF = "off";
    public final static String MUTE = "mute";
    public final static String VOLUME = "volume";
    public final static String SONG = "song";
    public final static String ALBUM = "album";
    public final static String ARTIST = "artist";
    public final static String IMAGE_URL = "image_url";

    public final static String NAME_HASH = "nameHash";
    public final static String GROUP_MEMBER_HASH = "groupMemberHash";

    // Time Constants
    public final static String HEART_BEAT_DELAY = "80";

    public static Set<ThingTypeUID> supportedThingTypes() {

        Set<ThingTypeUID> supportedThings = Sets.newHashSet(THING_TYPE_BRIDGE, THING_TYPE_GROUP, THING_TYPE_PLAYER);

        return supportedThings;
    }

}
