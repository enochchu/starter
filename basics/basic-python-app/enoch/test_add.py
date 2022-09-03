from .add import add

class TestAdd:
    def test_should_be_able_to_add(self):
        assert add(1, 2) == 3
