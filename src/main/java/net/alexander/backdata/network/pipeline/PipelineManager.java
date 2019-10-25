package net.alexander.backdata.network.pipeline;

import net.alexander.backdata.service.Service;

public class PipelineManager implements Service {




    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }
}
