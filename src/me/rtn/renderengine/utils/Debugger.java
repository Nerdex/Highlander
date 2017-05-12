package me.rtn.renderengine.utils;/*
 * 3DGa,e
 * Copyright (C) 2017 RapidTheNerd
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

public class Debugger {

    private static final String IN_GAME_DEBUG_PREFIX = "[GAME-DEBUG]";
    private static final String LOADING_DEBUG_PREFIX = "[LOADING-DEBUG]";
    private static final String EXCEPTION_CAUGHT_PREFIX = "[EXCEPTION-CAUGHT-DEBUG]";
    
    public static void useInGameDebug(String debugMessage){
        System.out.println(IN_GAME_DEBUG_PREFIX + debugMessage);
    }

    public static void useLoadDebug(String debugMessage){
        System.out.println(LOADING_DEBUG_PREFIX + debugMessage);
    }

    public static void useExceptionDebug(String debugMessage){
        System.out.println(EXCEPTION_CAUGHT_PREFIX + debugMessage);
    }
}
