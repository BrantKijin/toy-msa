package sample.userservice.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;
import sample.userservice.vo.ResponseOrder;

@Data
public class UserDto {
	private String email;
	private String name;
	private String pwd;
	private String userId;
	private Date createdAt;

	private String encryptedPwd;

	private List<ResponseOrder> orders;
}
