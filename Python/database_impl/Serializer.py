import pickle as pk

class Serializer:
    @classmethod
    def serialize_all(cls):
        from database_impl import SchoolData
        pklf = open("../database_impl/db/data.pkl", 'wb')
        pk.dump(SchoolData.GLOBAL_SCHOOL_DATA, pklf)
        pklf.close()