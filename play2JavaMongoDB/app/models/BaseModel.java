package models;

import org.bson.types.ObjectId;

public class BaseModel {
	public ObjectId _id;
	public ObjectId get_id() {
		return _id;
	}
	public void set_id(ObjectId _id) {
		this._id = _id;
	}
}
