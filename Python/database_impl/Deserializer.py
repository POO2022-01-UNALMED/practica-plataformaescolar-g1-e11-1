import pickle as pk

class Deserializer:
    @classmethod
    def deserialize_all(cls):
        from database_impl import SchoolData
        pklf = open("database_impl/db/data.pkl", 'rb')
        SchoolData.GLOBAL_SCHOOL_DATA = pk.load(pklf)
        pklf.close()