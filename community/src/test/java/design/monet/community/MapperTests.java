package design.monet.community;

import design.monet.community.dao.LoginTicketMapper;
import design.monet.community.dao.UserMapper;
import design.monet.community.entity.LoginTicket;
import design.monet.community.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MapperTests {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LoginTicketMapper loginTicketMapper;

    @Test
    public void testselectUser(){
        User user =userMapper.selectById(1);
        System.out.println(user);
    }

    @Test
    public void testinsert(){
        User user = new User();
        user.setUsername("test");
        user.setPassword("123456");
        user.setSalt("abc");
        user.setEmail("test@qq.com");
        user.setHeaderUrl("http://test.com/123.png");
        user.setCreateTime(new Date());

        int a=userMapper.insertUser(user);
        System.out.println(a);
        System.out.println(user.getId());
    }

    @Test
    public void updateUser(){
        int i = userMapper.updateStatus(1, 2);
        System.out.println(i);
        i=userMapper.updateHeader(1,"http://test.com/321.png");
        System.out.println(i);
        i=userMapper.updatePassword(1,"654321");
        System.out.println(i);
    }

    @Test
    public void testLoginTicket1(){
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setUserId(101);
        loginTicket.setTicket("abc");
        loginTicket.setExpired(new Date(System.currentTimeMillis()+ 1000*60*10));
        loginTicket.setStatus(0);
        loginTicketMapper.insertLoginTicket(loginTicket);
    }
    @Test
    public void testLoginTicket2(){
        LoginTicket abc = loginTicketMapper.selectByTicket("abc");
        System.out.println(abc);

        loginTicketMapper.updateStatus("abc",1);
        abc =loginTicketMapper.selectByTicket("abc");
        System.out.println(abc);
    }


}
