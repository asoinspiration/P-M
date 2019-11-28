# coding=utf-8
_author_ = "Monet"


import collections
import re


def contains_chinese(ustr):
    """判断unicode字符串中是否包含中文

    :param ustr:unicode字符串
    """

    return any('\u4e00' <= char <= '\u9fff' for char in ustr)


def strip_symbol(ustr):
    """删除unicode字符串中英文的标点符号

    :param ustr:unicode字符串
    """

    return re.sub('[’!"#$%&\'()*+,-./:;<=>?@[\\]^_`{|}~]+',' ',ustr)


def count_word(filename):
    """统计filename文本文件中非中文词的出现频率

    :param filename:需要统计词频的文本文件
    """

    # 返回值为dict，会自动进行计数
    word_counter=collections.Counter()

    with open(filename,'r',encoding='utf-8') as f:
        for line in f:
            #过滤中文
            if contains_chinese(line):
                continue

            # 去除英文标点符号
            line=strip_symbol(line)
            # 全部转为小写
            line=line.lower()

            word_counter.update([word for word in re.split('\s+',line) if word !=''])

    return dict(word_counter)


def get_top(filename,topk=10):
    """获取出现频率最高的topk个词

    :param filename:需要统计词频的文本文件
    :param topk:需要返回的出现频率最高的前topk个词，默认为10个
    """

    # 统计filename文本中的词频信息，保存到dict中
    word_dict=count_word(filename)
    # 对word_dict根据出现的词频进行降序排序
    topk_words=sorted(word_dict.items(),key=lambda x:x[1],reverse=True)
    return topk_words[:topk]


if '__main__'==__name__:
    import sys
    if len(sys.argv)!=3:
        print('Usage: {} filename topk'.format(sys.argv[0]),file=sys.stderr)
        sys.exit(0)
    top_words=get_top(sys.argv[1],int(sys.argv[2]))
    for k,v in top_words:
        print(k,v)