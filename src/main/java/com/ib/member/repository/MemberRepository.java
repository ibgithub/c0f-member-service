package com.ib.member.repository;

import com.ib.member.dto.MemberDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepository {

    private final JdbcTemplate jdbcTemplate;

    public MemberRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<MemberDto> findAll() {
        String sql = """
            SELECT 
                id, nrp, full_name, nick_name, gender,
                place_of_birth, date_of_birth,
                phone_number, email, address,
                city, province, country,
                created_by, created_date,
                updated_by, updated_date
            FROM member.members
        """;

        return jdbcTemplate.query(sql, memberRowMapper());
    }

    private RowMapper<MemberDto> memberRowMapper() {
        return (rs, rowNum) -> {
            MemberDto m = new MemberDto();
            m.setId(rs.getLong("id"));
            m.setNrp(rs.getString("nrp"));
            m.setFullName(rs.getString("full_name"));
            m.setNickName(rs.getString("nick_name"));
            m.setGender(rs.getString("gender"));
            m.setPlaceOfBirth(rs.getString("place_of_birth"));
            m.setDateOfBirth(
                    rs.getDate("date_of_birth") != null
                            ? rs.getDate("date_of_birth").toLocalDate()
                            : null
            );
            m.setPhoneNumber(rs.getString("phone_number"));
            m.setEmail(rs.getString("email"));
            m.setAddress(rs.getString("address"));
            m.setCity(rs.getString("city"));
            m.setProvince(rs.getString("province"));
            m.setCountry(rs.getString("country"));

            m.setCreatedBy(rs.getString("created_by"));
            m.setCreatedDate(rs.getTimestamp("created_date").toLocalDateTime());
            if (rs.getTimestamp("updated_date") != null) {
                m.setUpdatedDate(rs.getTimestamp("updated_date").toLocalDateTime());
            }
            m.setUpdatedBy(rs.getString("updated_by"));
            return m;
        };
    }
}