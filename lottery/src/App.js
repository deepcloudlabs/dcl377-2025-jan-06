// Components
// 1. stateless -> js function
// 2. stateful  -> js class
// 3. stateful  -> React Hooks -> js function

import Container from "./components/container";
import LotteryHooks from "./LotteryHooks";

function App() { // stateless, top-level component
                 // View -> i. Component-Based ii. Virtual DOM iii. Declarative/Functional Programming
  return (
      <Container>
          <LotteryHooks />
      </Container>
  );
}

export default App;
