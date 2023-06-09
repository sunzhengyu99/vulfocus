package com.sunzy.vulfocus.service.impl;

import com.sunzy.vulfocus.common.Result;
import com.sunzy.vulfocus.model.dto.LayoutDTO;
import com.sunzy.vulfocus.model.dto.UserDTO;
import com.sunzy.vulfocus.service.LayoutService;
import com.sunzy.vulfocus.utils.UserHolder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class LayoutServiceImplTest {
    @Resource
    private LayoutService layoutService;
    @Test
    void testCreateLayout() {
        UserDTO user = new UserDTO();
        user.setId(1);
        user.setSuperuser(true);
        user.setUsername("111");
        user.setRequestIp("127.0.0.1");

        UserHolder.saveUser(user);
        LayoutDTO layoutDTO = new LayoutDTO();

        layoutDTO.setName("demo");
        layoutDTO.setDesc("demo");
        layoutDTO.setImg("demo");
//        layoutDTO.setData("{'nodes': [{'name': 'Container', 'type': 'Container', 'id': '63yorm3qmro0', 'x': 140, 'y': 120, 'icon': 'data:image/png;base64,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', 'width': 200, 'height': 120, 'initW': 200, 'initH': 120, 'classType': 'T1', 'isLeftConnectShow': False, 'isRightConnectShow': True, 'containNodes': [], 'attrs': {'id': 'f009a105-44f7-4111-8e5a-cecc556515af', 'name': 'redis:3.2-alpine', 'desc': 'redis', 'port': '6379', 'open': True, 'vul_name': 'redis', 'raw': {'image_id': 'f009a105-44f7-4111-8e5a-cecc556515af', 'status': {'status': '', 'is_check': False, 'container_id': '', 'start_date': '', 'end_date': '', 'host': '', 'port': '', 'progress': 0, 'progress_status': '', 'task_id': '', 'now': 1607486074}, 'image_name': 'redis:3.2-alpine', 'image_vul_name': 'redis', 'image_port': '6379', 'image_desc': 'redis', 'rank': 2.5, 'is_ok': True, 'is_share': False, 'create_date': '2020-12-07T16:45:17.387204', 'update_date': '2020-12-07T16:45:17.420568'}}, 'isSelect': False}, {'name': 'Network', 'type': 'Network', 'id': '1860gad1nlpc', 'x': 440, 'y': 200, 'icon': 'data:image/png;base64,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', 'width': 200, 'height': 100, 'initW': 200, 'initH': 100, 'classType': 'T1', 'isLeftConnectShow': True, 'isRightConnectShow': False, 'containNodes': [], 'attrs': {'id': 'd9f9a0fb-c0d0-4831-9821-9947028c5a73', 'name': 'network-002', 'subnet': '172.13.2.0/24', 'gateway': '172.13.2.1', 'raw': {'net_work_id': 'd9f9a0fb-c0d0-4831-9821-9947028c5a73', 'net_work_client_id': '0489dcf918d39034bd39b96c6cb33f596a8cfc2262791d657d9974a6ffc20be2', 'create_user': 1, 'net_work_name': 'network-002', 'net_work_subnet': '172.13.2.0/24', 'net_work_gateway': '172.13.2.1', 'net_work_scope': 'local', 'net_work_driver': 'bridge', 'enable_ipv6': False, 'create_date': '2020-12-08T16:29:10.312053', 'update_date': '2020-12-08T16:29:10.312070'}}, 'isSelect': False}, {'name': 'Container', 'type': 'Container', 'id': 'gargmjrhv2o', 'x': 140, 'y': 280, 'icon': 'data:image/png;base64,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', 'width': 200, 'height': 120, 'initW': 200, 'initH': 120, 'classType': 'T1', 'isLeftConnectShow': False, 'isRightConnectShow': True, 'containNodes': [], 'attrs': {'id': 'f009a105-44f7-4111-8e5a-cecc556515af', 'name': 'redis:3.2-alpine', 'desc': 'redis', 'port': '6379', 'open': False, 'vul_name': 'redis', 'raw': {'image_id': 'f009a105-44f7-4111-8e5a-cecc556515af', 'status': {'status': '', 'is_check': False, 'container_id': '', 'start_date': '', 'end_date': '', 'host': '', 'port': '', 'progress': 0, 'progress_status': '', 'task_id': '', 'now': 1607486081}, 'image_name': 'redis:3.2-alpine', 'image_vul_name': 'redis', 'image_port': '6379', 'image_desc': 'redis', 'rank': 2.5, 'is_ok': True, 'is_share': False, 'create_date': '2020-12-07T16:45:17.387204', 'update_date': '2020-12-07T16:45:17.420568'}}, 'isSelect': True}], 'connectors': [{'id': 'm9o84w8vp1', 'type': 'Line', 'strokeW': 3, 'color': '#768699', 'targetNode': {'x': 440, 'y': 200, 'id': '1860gad1nlpc', 'width': 200, 'height': 100}, 'sourceNode': {'x': 140, 'y': 120, 'id': '63yorm3qmro0', 'width': 200, 'height': 120}, 'isSelect': False}, {'id': '1ebww27fghe', 'type': 'Line', 'strokeW': 3, 'color': '#768699', 'targetNode': {'x': 440, 'y': 200, 'id': '1860gad1nlpc', 'width': 200, 'height': 100}, 'sourceNode': {'x': 140, 'y': 280, 'id': 'gargmjrhv2o', 'width': 200, 'height': 120}}]}");

        layoutDTO.setData("{\"nodes\":[{\"name\":\"Network\",\"type\":\"Network\",\"id\":\"2ihp4sdd27y0\",\"x\":360,\"y\":40,\"icon\":\"data:image/png;base64,\",\"width\":200,\"height\":100,\"initW\":200,\"initH\":100,\"classType\":\"T1\",\"isLeftConnectShow\":true,\"isRightConnectShow\":true,\"containNodes\":[],\"attrs\":{\"id\":\"c392f65a-9302-4382-a668-53ccee8e4798\",\"name\":\"demo\",\"subnet\":\"192.168.5.1/24\",\"gateway\":\"192.168.5.1\",\"raw\":{\"net_work_id\":\"c392f65a-9302-4382-a668-53ccee8e4798\",\"net_work_client_id\":\"3ac8dca0f95cf8a63f45a9e73f0d4b26bf0a8e1cea3d76817b305a1abad0ca2f\",\"create_user\":1,\"net_work_name\":\"demo\",\"net_work_subnet\":\"192.168.5.1/24\",\"net_work_gateway\":\"192.168.5.1\",\"net_work_scope\":\"local\",\"net_work_driver\":\"bridge\",\"enable_ipv6\":false,\"create_date\":\"2023-04-18T23:20:05.917299\",\"update_date\":\"2023-04-18T23:20:05.917299\"}},\"isSelect\":false},{\"name\":\"Container\",\"type\":\"Container\",\"id\":\"49mc1oqura0\",\"x\":60,\"y\":160,\"icon\":\"data:image/png;base64,\",\"width\":200,\"height\":120,\"initW\":200,\"initH\":120,\"classType\":\"T1\",\"isLeftConnectShow\":false,\"isRightConnectShow\":true,\"containNodes\":[],\"attrs\":{\"id\":\"34e27d68-f81b-47ad-baae-dda1a4edd55d\",\"vul_name\":\"redis\",\"name\":\"redis:latest\",\"desc\":\"redis\",\"port\":\"6379\",\"open\":true,\"raw\":{\"image_id\":\"34e27d68-f81b-47ad-baae-dda1a4edd55d\",\"status\":{\"status\":\"\",\"is_check\":false,\"container_id\":\"\",\"start_date\":\"\",\"end_date\":\"\",\"host\":\"\",\"port\":\"\",\"progress\":0,\"progress_status\":\"\",\"task_id\":\"\",\"now\":1681831285},\"image_name\":\"redis:latest\",\"image_vul_name\":\"redis\",\"image_port\":\"6379\",\"image_desc\":\"redis\",\"rank\":2.5,\"is_ok\":true,\"is_share\":false,\"create_date\":\"2023-04-18T23:20:55.651619\",\"update_date\":\"2023-04-18T23:20:56.312785\"}},\"isSelect\":false},{\"name\":\"Container\",\"type\":\"Container\",\"id\":\"5hry5ipy6ok0\",\"x\":620,\"y\":220,\"icon\":\"data:image/png;base64,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\",\"width\":200,\"height\":120,\"initW\":200,\"initH\":120,\"classType\":\"T1\",\"isLeftConnectShow\":true,\"isRightConnectShow\":false,\"containNodes\":[],\"attrs\":{\"id\":\"34e27d68-f81b-47ad-baae-dda1a4edd55d\",\"vul_name\":\"redis\",\"name\":\"redis:latest\",\"desc\":\"redis\",\"port\":\"6379\",\"open\":true,\"raw\":{\"image_id\":\"34e27d68-f81b-47ad-baae-dda1a4edd55d\",\"status\":{\"status\":\"\",\"is_check\":false,\"container_id\":\"\",\"start_date\":\"\",\"end_date\":\"\",\"host\":\"\",\"port\":\"\",\"progress\":0,\"progress_status\":\"\",\"task_id\":\"\",\"now\":1681831299},\"image_name\":\"redis:latest\",\"image_vul_name\":\"redis\",\"image_port\":\"6379\",\"image_desc\":\"redis\",\"rank\":2.5,\"is_ok\":true,\"is_share\":false,\"create_date\":\"2023-04-18T23:20:55.651619\",\"update_date\":\"2023-04-18T23:20:56.312785\"}},\"isSelect\":true}],\"connectors\":[{\"id\":\"1oo4tk0gc4m\",\"type\":\"Line\",\"strokeW\":3,\"color\":\"#768699\",\"targetNode\":{\"x\":360,\"y\":40,\"id\":\"2ihp4sdd27y0\",\"width\":200,\"height\":100},\"sourceNode\":{\"x\":60,\"y\":160,\"id\":\"49mc1oqura0\",\"width\":200,\"height\":120},\"isSelect\":false},{\"id\":\"2poxxpk4rso\",\"type\":\"Line\",\"strokeW\":3,\"color\":\"#768699\",\"targetNode\":{\"x\":620,\"y\":220,\"id\":\"5hry5ipy6ok0\",\"width\":200,\"height\":120},\"sourceNode\":{\"x\":360,\"y\":40,\"id\":\"2ihp4sdd27y0\",\"width\":200,\"height\":100},\"isSelect\":false}]}\n");
        layoutService.CreateLayout(layoutDTO);
    }

    @Test
    void testRunLayout(){
        UserDTO user = new UserDTO();
        user.setId(1);
        user.setSuperuser(true);
        user.setUsername("111");
        user.setRequestIp("127.0.0.1");

        UserHolder.saveUser(user);
        layoutService.runLayout("cc66f3ef25184c19bd68e95933363f93");
    }

    @Test
    void testStopLayout() {
        UserDTO user = new UserDTO();
        user.setId(1);
        user.setSuperuser(true);
        user.setUsername("111");
        user.setRequestIp("127.0.0.1");

        UserHolder.saveUser(user);
        layoutService.stopLayout("cc66f3ef25184c19bd68e95933363f93");
    }

    @Test
    void testDeleteLayout() {
        UserDTO user = new UserDTO();
        user.setId(1);
        user.setSuperuser(true);
        user.setUsername("111");
        user.setRequestIp("127.0.0.1");

        UserHolder.saveUser(user);
        layoutService.deleteLayout("cc66f3ef25184c19bd68e95933363f93");
    }

    @Test
    void testFlagLayout() {
        UserDTO user = new UserDTO();
        user.setId(1);
        user.setSuperuser(true);
        user.setUsername("111");
        user.setRequestIp("127.0.0.1");

        UserHolder.saveUser(user);
        Result res = layoutService.flagLayout("cc66f3ef25184c19bd68e95933363f93", "flag{815d2799-0a0f-4412-a7a3-4d95fad5fbb5}");
        System.out.println(res);
    }

    @Test
    void testReleaserLayout() {
        UserDTO user = new UserDTO();
        user.setId(1);
        user.setSuperuser(true);
        user.setUsername("111");
        user.setRequestIp("127.0.0.1");

        UserHolder.saveUser(user);
        layoutService.releaseLayout("cc66f3ef25184c19bd68e95933363f93");
    }
}