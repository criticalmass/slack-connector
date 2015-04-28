package org.mule.modules.slack;


import org.mule.api.annotations.MetaDataKeyRetriever;
import org.mule.api.annotations.MetaDataRetriever;
import org.mule.api.annotations.components.MetaDataCategory;
import org.mule.common.metadata.DefaultMetaDataKey;
import org.mule.common.metadata.MetaData;
import org.mule.common.metadata.MetaDataKey;
import org.mule.modules.slack.client.model.channel.Channel;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by esteban on 19/02/15.
 */

@MetaDataCategory
public class ChannelCategory {

    public SlackConnector getMyconnector() {
        return myconnector;
    }

    public void setMyconnector(SlackConnector myconnector) {
        this.myconnector = myconnector;
    }

    @Inject
    private SlackConnector myconnector;

    @MetaDataKeyRetriever
    public List<MetaDataKey> getEntities() throws Exception {
        List<MetaDataKey> entities = new ArrayList<MetaDataKey>();
        List<Channel> channelList = myconnector.slack().getChannelList();
        for(Channel channel: channelList){
            entities.add(new DefaultMetaDataKey(channel.getId(),channel.getName() + " - " + channel.getId()));
        }
        
        return entities;
    }
    @MetaDataRetriever
    public MetaData describeEntity(MetaDataKey entityKey) throws Exception {
        return null;
    }


}