package com.hoteam.wolf.dao;

import org.springframework.stereotype.Component;

import com.hoteam.wolf.domain.BoxVerifyRecord;

@Component("boxVerifyRecordDao")
public class BoxVerifyRecordDao extends BaseDao {

	public BoxVerifyRecord save(final BoxVerifyRecord boxVerifyRecord) throws Exception {
		boxVerifyRecord.prePersist();
		saveWithPk(boxVerifyRecord);
		return boxVerifyRecord;
	}

	public BoxVerifyRecord update(final BoxVerifyRecord boxVerifyRecord) {
		boxVerifyRecord.preUpdate();
		baseSaveUpdate(boxVerifyRecord);
		return boxVerifyRecord;
	}

	public void delete(BoxVerifyRecord boxVerifyRecord) {
		this.baseDelete(boxVerifyRecord);
	}

	public void delete(Long id) {
		BoxVerifyRecord boxVerifyRecord = new BoxVerifyRecord();
		boxVerifyRecord.setId(id);
		this.baseDelete(boxVerifyRecord);
	}

	public void deleteByBox(Long boxId) {
		this.jdbcTemplate.execute("delete from box_verify_record where box_id=" + boxId);
	}
}
