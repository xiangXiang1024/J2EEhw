package com.j2ee.server.service.client;

import com.j2ee.server.dao.AddressDao;
import com.j2ee.server.dao.ClientDao;
import com.j2ee.server.dao.OrderDao;
import com.j2ee.server.entity.Clients;
import com.j2ee.server.entity.Orders;
import com.j2ee.server.entity.Address;
import com.j2ee.server.util.AddressState;
import com.j2ee.server.util.OrderState;
import com.j2ee.server.vo.AddressVO;
import com.j2ee.server.vo.ClientInfoVO;
import com.j2ee.server.vo.OrderDetailVO;
import com.j2ee.server.vo.OrderInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 许杨
 * @Description: 客户模块
 * @Date: 2019/1/26
 */
@Service
public class ClientImpl implements ClientService {
    private ClientDao clientDao;
    private OrderDao orderDao;
    private AddressDao addressDao;
    @Autowired
    public ClientImpl(ClientDao clientDao, OrderDao orderDao, AddressDao addressDao) {
        this.clientDao = clientDao;
        this.orderDao = orderDao;
        this.addressDao = addressDao;
    }


    /**
     * 会员查看历史订单
     * @param clientName    会员昵称
     * @return              订单详情
     */
    @Override
    public List<OrderDetailVO> seeHistoryOrders(String clientName){
        List<Orders> orderList = orderDao.findDistinctByClientName(clientName);

        if(orderList == null) {
            return new ArrayList<>();
        }

        List<OrderDetailVO> infoVOList = new ArrayList<>();
        for(Orders order : orderList) {
            OrderState state = order.getState();
            if(state == OrderState.FINISHED || state == OrderState.CANCELED) {
                infoVOList.add(new OrderDetailVO(order));
            }
        }

        return infoVOList;
    }

    /**
     * 查看订单概要
     * @param clientName 会员昵称
     * @param state      订单状态
     * @return 订单概要
     */
    @Override
    public List<OrderDetailVO> seeOrders(String clientName, OrderState state) {
        List<Orders> orderList = orderDao.findDistinctByClientNameAndState(clientName, state);

        if(orderList == null) {
            return new ArrayList<>();
        }

        List<OrderDetailVO> infoVOList = new ArrayList<>();
        for(Orders order : orderList) {
            infoVOList.add(new OrderDetailVO(order));
        }

        System.out.println("OrderDetailVOs: ");
        for(OrderDetailVO vo : infoVOList) {
            vo.print();
        }

        return infoVOList;
    }

    /**
     * 查看会员信息
     * @param name  会员昵称
     * @return      会员信息
     */
    @Override
    public ClientInfoVO seeClientInfo(String name) {
        Clients client = clientDao.findById(name).get();

        ClientInfoVO info = new ClientInfoVO(client);

        List<Address> addressList = addressDao.findDistinctByClientName(name);

        List<AddressVO> addressVOS = new ArrayList<>();

        if(addressList != null) {
            for(Address address : addressList) {
                if(address.getState() != AddressState.DISCARD) {
                    AddressVO vo = new AddressVO(address.getId(), address.getDistrict().getStr(), address.getDetail());
                    addressVOS.add(vo);
                }
            }
        }

        info.setAddressList(addressVOS);

        return info;
    }

    /**
     * 会员查看统计信息
     * @param client    会员
     * @param start     开始时间
     * @param end       结束时间
     * @param min       最低金额
     * @param max       最高金额
     * @param canteen   餐厅名
     * @return          订单详情
     */
    @Override
    public List<OrderDetailVO> seeStatistics(String client, String start, String end, Double min, Double max, String canteen) {
        List<Orders> orderList = orderDao.findDistinctByClientName(client);

        List<OrderDetailVO> voList = new ArrayList<>();
        if(orderList == null) {
            return voList;
        }

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        for(Orders o : orderList) {
            System.out.println("id: "+o.getId());
            // 开始时间
            if(start != null && start.length()>0) {
                LocalDateTime startTime = LocalDateTime.parse(start+" 00:00:00", df);
                if(o.getOrderTime().isBefore(startTime)) {
                    continue;
                }
            }
//            System.out.println("开始时间 id: "+o.getId());

            // 结束时间
            if(end != null && end.length()>0) {
                LocalDateTime endTime = LocalDateTime.parse(end+" 23:59:59", df);
                if(o.getOrderTime().isAfter(endTime)) {
                    continue;
                }
            }
//            System.out.println("结束时间 id: "+o.getId());

            // 最小金额
            if(min != null) {
                if(o.getFinalMoney() < min) {
                    continue;
                }
            }
//            System.out.println("最小金额 id: "+o.getId());

            // 最大金额
            if(max != null) {
                if(o.getFinalMoney() > max) {
                    continue;
                }
            }
//            System.out.println("最大金额 id: "+o.getId());

            // 餐厅名
            if(canteen != null && canteen.length() > 0) {
//                System.out.println("canteen: "+o.getCanteen().getName());
                if(!o.getCanteen().getName().contains(canteen)) {
                    continue;
                }
            }
//            System.out.println("餐厅名 id: "+o.getId());

            voList.add(new OrderDetailVO(o));
        }
        return voList;
    }

    /**
     * 获得会员的送餐地址
     * @param client 会员昵称
     * @return 送餐地址
     */
    @Override
    public List<AddressVO> getAddress(String client) {
        List<Address> addressList = addressDao.findDistinctByClientName(client);

        if(addressList == null) {
            return new ArrayList<>();
        }

        List<AddressVO> voList = new ArrayList<>();
        for(Address a : addressList) {
            AddressVO str = new AddressVO(a);
            voList.add(str);
        }

        return voList;
    }
}
