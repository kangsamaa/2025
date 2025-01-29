import sys

if __name__ == "__main__":
    args = sys.argv[1:]
    fomatted_args = " ".join(args)
    print("hello from python! arguments:") 
    print(fomatted_args) #문자열로 포맷을 변환하기
    #파이썬은 args로 매개변수 주고받음 자바호출단에서 defaultValue에 world로 셋팅한거 테스트해보기