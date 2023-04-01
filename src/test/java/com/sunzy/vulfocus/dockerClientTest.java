package com.sunzy.vulfocus;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.ListImagesCmd;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.Image;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.okhttp.OkDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;
import com.sunzy.vulfocus.utils.DockerTools;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SpringBootTest
public class dockerClientTest {
    private DockerClient dockerClient = null;

    @Test
    void testClient() {
        connectDocker();

        HostConfig hostConfig = new HostConfig();
//        List<String> cmd = new ArrayList<>();
//        cmd.add("echo hello docker");
//        String imageName = "alpine";
//        String demo = createContainer(imageName, "demo", hostConfig, cmd);
//        System.out.println(demo);
        List<Image> imageList = dockerClient.listImagesCmd().exec();
        for (Image image : imageList) {
            System.out.println(image);
        }

        List<Container> containerList = dockerClient.listContainersCmd().withShowAll(true).exec();
        for (Container container : containerList) {
            System.out.println(container);
        }
    }

    public void connectDocker() {
        DockerClientConfig dockerClientConfig = DefaultDockerClientConfig
                .createDefaultConfigBuilder()
                .build();
        DockerHttpClient httpClient = new OkDockerHttpClient.Builder()
                .dockerHost(dockerClientConfig.getDockerHost())
                .sslConfig(dockerClientConfig.getSSLConfig())
                .build();
        dockerClient = DockerClientImpl.getInstance(dockerClientConfig, httpClient);
    }

    public String createContainer(String imageName, String containerName, HostConfig hostConfig, List<String> cmd){
        CreateContainerResponse container = dockerClient.createContainerCmd(imageName)
                .withName(containerName)
                .withHostConfig(hostConfig)
                .withCmd(cmd)
                .exec();
        return container.getId();
    }


    @Test
    void test1() {
        HostConfig hostConfig = new HostConfig();
        List<String> cmd = new ArrayList<>();
        cmd.add("echo hello hello");
        String imageName = "alpine";
        String id = DockerTools.createContainer(imageName, "demo2", hostConfig, cmd);
        System.out.println(id);

    }
}
