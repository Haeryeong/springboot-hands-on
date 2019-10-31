package kr.co.fastcampuse.web.account;

import kr.co.fastcampuse.web.account.dto.AccountDto;
import kr.co.fastcampuse.web.account.model.AccountModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {
    @Autowired AccountRepository accountRepository;

    public List<AccountDto> list() {
        List<AccountModel> list = accountRepository.list();
        return list.stream().map(x -> x.toDto()).collect(Collectors.toList());
    }

    public Page<AccountDto> page(Pageable pageable) {
        Page<AccountModel> page = accountRepository.findAll(pageable);
        return page.map(AccountModel::toDto);
    }

    public void create(AccountDto dto) {
        accountRepository.create(dto.toModel());
    }
}
