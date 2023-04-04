package hocheoltech.boos.service;


import hocheoltech.boos.domain.BusinessCategory;
import hocheoltech.boos.domain.Members;
import hocheoltech.boos.dto.MembersJoinDto;
import hocheoltech.boos.dto.UpdateMembersDto;
import hocheoltech.boos.exception.ErrorMessage;
import hocheoltech.boos.repository.BusinessCategoryRepository;
import hocheoltech.boos.repository.MembersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import java.util.concurrent.RejectedExecutionException;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class MemberService implements UserDetailsService {

//    private final MemberMapper memberMapper;
    private final MembersRepository membersRepository;
    private final BusinessCategoryRepository businessCategoryRepository;

    // 회원가입
    @Transactional
    public MembersJoinDto saveMember(MembersJoinDto membersJoinDto){
        if(membersRepository.existsById(membersJoinDto.getId())){
            throw new RejectedExecutionException(ErrorMessage.DUPLICATE_MEMBER_ID.getMsg());
        }
        BusinessCategory businessCategory = businessCategoryRepository.findByCategoryName(membersJoinDto.getBusinessCategory()).orElseThrow(
                () -> new NoSuchElementException(ErrorMessage.NOT_EXIST_BUSINESS_CATEGORY_NAME.getMsg()));


        LocalDate openTime = LocalDate.parse(membersJoinDto.getOpenTime(), DateTimeFormatter.ofPattern("yyyyMMdd"));

        Members members = Members.builder()
                .id(membersJoinDto.getId())
                .password(membersJoinDto.getPassword())
                .name(membersJoinDto.getName())
                .businessRegNum(membersJoinDto.getBusinessRegNum())
                .businessCategory(businessCategory)
                .nickname(membersJoinDto.getNickname())
                .openTime(openTime)
                .build();

        Members savedMembers = membersRepository.save(members);
        String openTime2 = savedMembers.getOpenTime().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        MembersJoinDto membersDto = MembersJoinDto.builder()
                .id(savedMembers.getId())
                .name(savedMembers.getName())
                .nickname(savedMembers.getNickname())
                .businessRegNum(savedMembers.getBusinessRegNum())
                .businessCategory(savedMembers.getBusinessCategory().getCategoryName())
                .openTime(openTime2)
                .build();

        return membersDto;
    }

    public Members findMember(String id){
        Members members = membersRepository.findById(id).orElseThrow( // 해당 사용자가 없으면 Throw
                () -> new NoSuchElementException(ErrorMessage.NOT_EXIST_MEMBER.getMsg()));
        return members;
    }
    @Transactional
    public void deleteMember(String id){
        if(!membersRepository.existsById(id)){
            throw new NoSuchElementException(ErrorMessage.NOT_EXIST_MEMBER.getMsg());
        }
        membersRepository.deleteById(id);
    }
    @Transactional
    public void modifyMember(UpdateMembersDto updateMembersDto){
        Members members = membersRepository.findById(updateMembersDto.getId()).orElseThrow(
                () -> new NoSuchElementException(ErrorMessage.NOT_EXIST_MEMBER.getMsg())
        );
        members.updateMemberInfo(updateMembersDto.getPassword(),updateMembersDto.getNickname());
    }

    // jwt 인증,,
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        return membersRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(ErrorMessage.NOT_EXIST_MEMBER.getMsg()));
    }
}
