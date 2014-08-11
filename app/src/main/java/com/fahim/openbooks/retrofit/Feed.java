package com.fahim.openbooks.retrofit;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementMap;
import org.simpleframework.xml.Root;

import java.util.List;
import java.util.Map;

/**
 * Created by Fahim on 8/10/2014.
 */
@Root(strict=false)
public class Feed {

    @ElementList(inline=true)
    public List<Entry> list;

    @Root(strict=false)
    public static class Entry {

        @Element
        public String title;

        @ElementMap(entry="link", key="rel", attribute=true, inline=true, value="href")
        public Map<String, String> map;
    }
}