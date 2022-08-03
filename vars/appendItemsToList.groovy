// Manage a comma separated list
//
// Main rationale is to deal with the vagaries of
// environment variable access in Jenkinsfiles
//
// It leverages groovy's "truthiness" rules
//
def call(
    String list,
    String items
) {
    if (list && (list.trim().size() != 0)) {
        if (items && (items.trim().size() != 0)) {
            return [list.trim(), items.trim()].join(',')
        } else {
            return list.trim()
        }
    } else {
        if (items) {
            return items.trim()
        }
    }
    return ''
}
