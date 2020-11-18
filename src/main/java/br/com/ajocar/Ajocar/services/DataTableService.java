//package br.com.ajocar.Ajocar.services;
//
//
//import br.com.ajocar.Ajocar.model.paging.Column;
//import br.com.ajocar.Ajocar.model.paging.Order;
//import br.com.ajocar.Ajocar.model.paging.Page;
//import br.com.ajocar.Ajocar.model.paging.PagingRequest;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.util.StringUtils;
//
//import java.util.Comparator;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class DataTableService {
//
//    private static final Comparator<Object> EMPTY_COMPARATOR = (e1, e2) -> 0;
//
//
//
//    public Page<Object> getObjects(PagingRequest pagingRequest, List objects, JpaRepository repository) {
//        ObjectMapper objectMapper = new ObjectMapper();
//
//
//             objects = repository.findAll();
//
//            return getPage(objects, pagingRequest);
//    }
//
//    private Page<Object> getPage(List objects, PagingRequest pagingRequest) {
//        List filtered = objects.stream()
//                .sorted(sortEmployees(pagingRequest))
//                .filter(filterEmployees(pagingRequest))
//                .skip(pagingRequest.getStart())
//                .limit(pagingRequest.getLength())
//                .collect(Collectors.toList());
//
//        long count = employees.stream()
//                .filter(filterEmployees(pagingRequest))
//                .count();
//
//        Page<Employee> page = new Page<>(filtered);
//        page.setRecordsFiltered((int) count);
//        page.setRecordsTotal((int) count);
//        page.setDraw(pagingRequest.getDraw());
//
//        return page;
//    }
//
//    private Predicate<Employee> filterEmployees(PagingRequest pagingRequest) {
//        if (pagingRequest.getSearch() == null || StringUtils.isEmpty(pagingRequest.getSearch()
//                .getValue())) {
//            return employee -> true;
//        }
//
//        String value = pagingRequest.getSearch()
//                .getValue();
//
//        return employee -> employee.getName()
//                .toLowerCase()
//                .contains(value)
//                || employee.getPosition()
//                .toLowerCase()
//                .contains(value)
//                || employee.getOffice()
//                .toLowerCase()
//                .contains(value);
//    }
//
//    private Comparator<Object> sortEmployees(PagingRequest pagingRequest) {
//        if (pagingRequest.getOrder() == null) {
//            return EMPTY_COMPARATOR;
//        }
//
//        try {
//            Order order = pagingRequest.getOrder()
//                    .get(0);
//
//            int columnIndex = order.getColumn();
//            Column column = pagingRequest.getColumns()
//                    .get(columnIndex);
//
//            Comparator<Object> comparator = EmployeeComparators.getComparator(column.getData(), order.getDir());
//            if (comparator == null) {
//                return EMPTY_COMPARATOR;
//            }
//
//            return comparator;
//
//        } catch (Exception e) {
//            log.error(e.getMessage(), e);
//        }
//
//        return EMPTY_COMPARATOR;
//    }
//}
