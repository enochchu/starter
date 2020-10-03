class Enoch:
    name: str

    def __init__(self, name:str):
        self.name = name

    def saysHello(self):
        print(f"Hello { self.name }!")